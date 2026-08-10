import { useCallback, useEffect, useMemo, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { PageTitle } from "../../shared/ui/PageTitle";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";
import "../../shared/styles/AuthForm.scss";
import { Button } from "../../shared/ui/Button/Button";
import { useAuthStore } from "../../shared/store/authStore";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { API_BASE_URL } from "../../shared/api/config";
import editIcon from "../../shared/ui/Icons/edit.svg";

export const AccountPage = () => {
  const logout = useAuthStore((state) => state.logout);
  const clearFavorites = useFavoritesStore((state) => state.clearFavorites);
  const navigate = useNavigate();
  const [avatarUrl, setAvatarUrl] = useState<string | null>(null);
  const [avatarError, setAvatarError] = useState(false);
  const [isAvatarUpdating, setIsAvatarUpdating] = useState(false);
  const [isSaving, setIsSaving] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [editableField, setEditableField] = useState<"nickname" | "email" | "password" | null>(null);
  const avatarObjectUrlRef = useRef<string | null>(null);

  const initialNicknameValue = useMemo(() => {
    const storedNickname = localStorage.getItem("userNickname");
    if (storedNickname) {
      return storedNickname;
    }

    const email = localStorage.getItem("userEmail") || "";
    return email.split("@")[0] || "";
  }, []);
  const initialEmailValue = useMemo(() => localStorage.getItem("userEmail") || "", []);
  const authToken = useMemo(() => localStorage.getItem("authToken") || "", []);

  const [initialNickname, setInitialNickname] = useState(initialNicknameValue);
  const [initialEmail, setInitialEmail] = useState(initialEmailValue);
  const [nickname, setNickname] = useState(initialNicknameValue);
  const [email, setEmail] = useState(initialEmailValue);
  const [password, setPassword] = useState("");

  const fetchAvatar = useCallback(async () => {
    if (!authToken) {
      return;
    }

    const response = await fetch(`${API_BASE_URL}/users/avatar-get`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    });

    if (!response.ok) {
      if (response.status === 500) {
        setAvatarError(true);
        setAvatarUrl(null);
        return;
      }
      throw new Error("Failed to load avatar");
    }

    const blob = await response.blob();
    const nextObjectUrl = URL.createObjectURL(blob);
    if (avatarObjectUrlRef.current) {
      URL.revokeObjectURL(avatarObjectUrlRef.current);
    }
    avatarObjectUrlRef.current = nextObjectUrl;
    setAvatarError(false);
    setAvatarUrl(nextObjectUrl);
  }, [authToken]);

  useEffect(() => {
    if (!authToken) {
      return;
    }

    let active = true;
    const loadAvatar = async () => {
      try {
        if (active) {
          await fetchAvatar();
        }
      } catch (error) {
        console.error("Avatar fetch failed:", error);
        if (active) {
          setAvatarError(true);
        }
      }
    };

    loadAvatar();
    return () => {
      active = false;
      if (avatarObjectUrlRef.current) {
        URL.revokeObjectURL(avatarObjectUrlRef.current);
        avatarObjectUrlRef.current = null;
      }
    };
  }, [authToken, fetchAvatar]);

  const handleLogout = () => {
    clearFavorites();
    logout();
    navigate("/");
  };

  const hasChanges =
    nickname !== initialNickname || email !== initialEmail || password.trim().length > 0;

  const handleAvatarChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    event.target.value = "";

    if (!file || !authToken) {
      return;
    }

    setError("");
    setSuccess("");
    setIsAvatarUpdating(true);

    try {
      const formData = new FormData();
      formData.append("file", file);

      const response = await fetch(`${API_BASE_URL}/users/avatar-update`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
        body: formData,
      });

      const data = (await response.json().catch(() => null)) as
        | { message?: string; error?: string }
        | null;

      if (!response.ok) {
        throw new Error(data?.message || data?.error || "Failed to update avatar");
      }

      await fetchAvatar();
    } catch (updateError) {
      const message = updateError instanceof Error ? updateError.message : "Failed to update avatar";
      setError(message);
    } finally {
      setIsAvatarUpdating(false);
    }
  };

  const handleSave = async () => {
    if (!hasChanges || !authToken) {
      return;
    }

    const nextNickname = nickname.trim();
    const nextEmail = email.trim();
    const nextPassword = password.trim();
    const nicknameChanged = nextNickname !== initialNickname;
    const passwordChanged = nextPassword.length > 0;
    const emailChanged = nextEmail !== initialEmail;

    setError("");
    setSuccess("");

    if (!nextNickname || !nextEmail) {
      setError("Nickname and e-mail are required");
      return;
    }

    if ((nicknameChanged || passwordChanged) && !nextPassword) {
      setError("Enter password to save profile changes");
      return;
    }

    setIsSaving(true);
    try {
      let savedNickname = nextNickname;
      if (nicknameChanged || passwordChanged) {
        const response = await fetch(`${API_BASE_URL}/users/profile-update`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${authToken}`,
          },
          body: JSON.stringify({
            userNickname: nextNickname,
            password: nextPassword,
            repeatPassword: nextPassword,
          }),
        });

        const data = (await response.json().catch(() => null)) as
          | { userNickname?: string; message?: string; error?: string }
          | null;

        if (!response.ok) {
          throw new Error(data?.message || data?.error || "Failed to save profile");
        }

        savedNickname = data?.userNickname || nextNickname;
        localStorage.setItem("userNickname", savedNickname);
      } else {
        localStorage.setItem("userNickname", nextNickname);
      }

      if (emailChanged) {
        localStorage.setItem("userEmail", nextEmail);
      }

      setInitialNickname(savedNickname);
      setInitialEmail(nextEmail);
      setNickname(savedNickname);
      setEmail(nextEmail);
      setPassword("");
      setEditableField(null);
      setSuccess("Profile saved");
    } catch (saveError) {
      const message = saveError instanceof Error ? saveError.message : "Failed to save profile";
      setError(message);
    } finally {
      setIsSaving(false);
    }
  };

  const handleDeleteAccount = async () => {
    if (!authToken) {
      return;
    }

    const isConfirmed = window.confirm("Are you sure you want to delete your account?");
    if (!isConfirmed) {
      return;
    }

    setError("");
    setSuccess("");
    setIsDeleting(true);
    try {
      const response = await fetch(`${API_BASE_URL}/users`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
      });

      if (!response.ok) {
        const data = (await response.json().catch(() => null)) as
          | { message?: string; error?: string }
          | null;
        throw new Error(data?.message || data?.error || "Failed to delete account");
      }

      handleLogout();
    } catch (deleteError) {
      const message =
        deleteError instanceof Error ? deleteError.message : "Failed to delete account";
      setError(message);
    } finally {
      setIsDeleting(false);
    }
  };

  return (
    <>
      <PageBackground variant="account" />
      <div className="w-full flex flex-col flex-1 pb-20 relative z-10">
        <PageTitle title="Account" />
        <div className="flex-1 flex flex-col justify-center w-full pb-20 px-4 md:px-0">
          <div className="flex flex-col items-center justify-center auth-form w-full">
            <div className="flex flex-col items-center gap-[32px] w-full md:w-[700px]">
              <div className="flex flex-col items-center gap-[24px] w-full">
                <label className="w-[190px] h-[190px] rounded-full overflow-hidden border border-white/20 bg-white/10 flex items-center justify-center cursor-pointer relative">
                  <input
                    type="file"
                    accept="image/*"
                    onChange={handleAvatarChange}
                    className="absolute inset-0 opacity-0 cursor-pointer"
                    disabled={isAvatarUpdating}
                  />
                  {avatarUrl && !avatarError ? (
                    <img
                      src={avatarUrl}
                      alt="User avatar"
                      className="w-full h-full object-cover"
                    />
                  ) : (
                    <span className="text-[#FFFBE4] opacity-80 text-center">No avatar</span>
                  )}
                  <span className="absolute bottom-[10px] left-1/2 -translate-x-1/2 w-[24px] h-[24px] rounded-full bg-[#00192D]/70 flex items-center justify-center pointer-events-none">
                    <img src={editIcon} alt="" className="w-[14px] h-[14px]" />
                  </span>
                </label>
                <p className="text-[#FFFBE4] text-[18px] opacity-90 text-center">
                  {nickname || "Guest"}
                </p>
              </div>
              <div className="w-full md:w-[450px] flex flex-col gap-[24px]">
                <div className="flex flex-col gap-[8px]">
                  <label className="text-[#FFFBE4] text-[24px]">Nickname</label>
                  <div className="relative w-full">
                    <input
                      type="text"
                      value={nickname}
                      onClick={() => setEditableField("nickname")}
                      readOnly={editableField !== "nickname"}
                      onChange={(event) => setNickname(event.target.value)}
                      className="auth-form__input w-full pr-[44px]"
                    />
                    <button
                      type="button"
                      onClick={() => setEditableField("nickname")}
                      className="absolute right-[12px] top-1/2 -translate-y-1/2 w-[20px] h-[20px] flex items-center justify-center"
                    >
                      <img src={editIcon} alt="Edit nickname" className="w-[20px] h-[20px]" />
                    </button>
                  </div>
                </div>
                <div className="flex flex-col gap-[8px]">
                  <label className="text-[#FFFBE4] text-[24px]">E-mail</label>
                  <div className="relative w-full">
                    <input
                      type="email"
                      value={email}
                      onClick={() => setEditableField("email")}
                      readOnly={editableField !== "email"}
                      onChange={(event) => setEmail(event.target.value)}
                      className="auth-form__input w-full pr-[44px]"
                    />
                    <button
                      type="button"
                      onClick={() => setEditableField("email")}
                      className="absolute right-[12px] top-1/2 -translate-y-1/2 w-[20px] h-[20px] flex items-center justify-center"
                    >
                      <img src={editIcon} alt="Edit e-mail" className="w-[20px] h-[20px]" />
                    </button>
                  </div>
                </div>
                <div className="flex flex-col gap-[8px]">
                  <label className="text-[#FFFBE4] text-[24px]">Password</label>
                  <div className="relative w-full">
                    <input
                      type="password"
                      value={password}
                      placeholder="********"
                      onClick={() => setEditableField("password")}
                      readOnly={editableField !== "password"}
                      onChange={(event) => setPassword(event.target.value)}
                      className="auth-form__input w-full pr-[44px]"
                    />
                    <button
                      type="button"
                      onClick={() => setEditableField("password")}
                      className="absolute right-[12px] top-1/2 -translate-y-1/2 w-[20px] h-[20px] flex items-center justify-center"
                    >
                      <img src={editIcon} alt="Edit password" className="w-[20px] h-[20px]" />
                    </button>
                  </div>
                </div>
              </div>
            </div>

            {error && (
              <div className="text-red-500 mt-4 text-center w-full md:w-[450px]">
                {error}
              </div>
            )}
            {success && (
              <div className="text-[#FFFBE4] mt-4 text-center w-full md:w-[450px]">
                {success}
              </div>
            )}

            {hasChanges && (
              <Button
                type="button"
                onClick={handleSave}
                disabled={isSaving}
                className="w-full md:w-[450px] mt-[56px] primary-button__login"
              >
                Save
              </Button>
            )}

            <Button
              type="button"
              onClick={handleLogout}
              className={`w-full md:w-[450px] primary-button__login ${hasChanges ? "mt-[24px]" : "mt-[56px]"}`}
            >
              Log Out
            </Button>

            <Button
              type="button"
              onClick={handleDeleteAccount}
              disabled={isDeleting}
              className="w-full md:w-[450px] mt-[24px] primary-button__login"
            >
              Delete account
            </Button>
          </div>
        </div>
      </div>
    </>
  );
};
