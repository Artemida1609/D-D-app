import { useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import { PageTitle } from "../../shared/ui/PageTitle";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";
import { Button } from "../../shared/ui/Button/Button";
import { useAuthStore } from "../../shared/store/authStore";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { API_BASE_URL } from "../../shared/api/config";

export const AccountPage = () => {
  const logout = useAuthStore((state) => state.logout);
  const clearFavorites = useFavoritesStore((state) => state.clearFavorites);
  const navigate = useNavigate();
  const [avatarUrl, setAvatarUrl] = useState<string | null>(null);
  const [avatarError, setAvatarError] = useState(false);

  const userNickname = useMemo(() => {
    const storedNickname = localStorage.getItem("userNickname");
    if (storedNickname) {
      return storedNickname;
    }

    const email = localStorage.getItem("userEmail") || "";
    return email.split("@")[0] || "";
  }, []);
  const userEmail = useMemo(() => localStorage.getItem("userEmail") || "", []);

  useEffect(() => {
    const authToken = localStorage.getItem("authToken");
    if (!authToken) {
      return;
    }

    let active = true;
    let objectUrl: string | null = null;
    const fetchAvatar = async () => {
      try {
        const response = await fetch(`${API_BASE_URL}/users/avatar-get`, {
          headers: {
            Authorization: `Bearer ${authToken}`,
          },
        });

        if (!response.ok) {
          if (response.status === 500) {
            if (active) {
              setAvatarError(true);
            }
            return;
          }
          throw new Error("Failed to load avatar");
        }

        const blob = await response.blob();
        objectUrl = URL.createObjectURL(blob);
        if (active) {
          setAvatarUrl(objectUrl);
        }
      } catch (error) {
        console.error("Avatar fetch failed:", error);
        if (active) {
          setAvatarError(true);
        }
      }
    };

    fetchAvatar();
    return () => {
      active = false;
      if (objectUrl) {
        URL.revokeObjectURL(objectUrl);
      }
    };
  }, []);

  const handleLogout = () => {
    clearFavorites();
    logout();
    navigate("/");
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
                <div className="w-[190px] h-[190px] rounded-full overflow-hidden border border-white/20 bg-white/10 flex items-center justify-center">
                  {avatarUrl && !avatarError ? (
                    <img
                      src={avatarUrl}
                      alt="User avatar"
                      className="w-full h-full object-cover"
                    />
                  ) : (
                    <span className="text-[#FFFBE4] opacity-80 text-center">No avatar</span>
                  )}
                </div>
                <p className="text-[#FFFBE4] text-[18px] opacity-90 text-center">
                  {userNickname || "Guest"}
                </p>
              </div>
              <div className="w-full md:w-[380px] flex flex-col gap-[24px]">
                <div className="flex flex-col gap-[8px]">
                  <label className="text-white text-[24px]">Nickname</label>
                  <input
                    type="text"
                    value={userNickname}
                    readOnly
                    className="auth-form__input w-full"
                  />
                </div>
                <div className="flex flex-col gap-[8px]">
                  <label className="text-white text-[24px]">E-mail</label>
                  <input
                    type="email"
                    value={userEmail}
                    readOnly
                    className="auth-form__input w-full"
                  />
                </div>
                <div className="flex flex-col gap-[8px]">
                  <label className="text-white text-[24px]">Password</label>
                  <input
                    type="password"
                    value="********"
                    readOnly
                    className="auth-form__input w-full"
                  />
                </div>
              </div>
            </div>

            <Button
              type="button"
              onClick={handleLogout}
              className="w-full md:w-[450px] mt-[56px] primary-button__login"
            >
              Log Out
            </Button>
          </div>
        </div>
      </div>
    </>
  );
};
