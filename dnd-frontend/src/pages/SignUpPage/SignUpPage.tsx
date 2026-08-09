import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { PageTitle } from "../../shared/ui/PageTitle";
import "../../shared/styles/AuthForm.scss";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";
import { Button } from "../../shared/ui/Button/Button";
import { useAuthStore } from "../../shared/store/authStore";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { API_BASE_URL } from "../../shared/api/config";

export const SignUpPage = () => {
  const [userNickname, setUserNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPassword, setRepeatPassword] = useState("");
  const [avatarFile, setAvatarFile] = useState<File | null>(null);
  const [error, setError] = useState("");

  const login = useAuthStore((state) => state.login);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError("");

    if (password !== repeatPassword) {
      setError("Passwords do not match");
      return;
    }

    try {
      const formData = new FormData();
      const dataPart = {
        email,
        password,
        repeatPassword,
        userNickname,
      };
      formData.append("data", JSON.stringify(dataPart));
      if (avatarFile) {
        formData.append("avatar", avatarFile);
      }

      const registrationResponse = await fetch(`${API_BASE_URL}/auth/registration`, {
        method: "POST",
        body: formData,
      });

      const registrationData = (await registrationResponse.json().catch(() => null)) as
        | { id?: number; email?: string; userNickname?: string; message?: string; error?: string }
        | null;
      if (!registrationResponse.ok) {
        throw new Error(registrationData?.message || registrationData?.error || "Registration failed");
      }

      const loginResponse = await fetch(`${API_BASE_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      const loginData = (await loginResponse.json().catch(() => null)) as
        | { token?: string; refreshToken?: string; message?: string; error?: string }
        | null;
      if (!loginResponse.ok || !loginData?.token) {
        throw new Error(loginData?.message || loginData?.error || "Registration succeeded but automatic login failed.");
      }

      login(loginData.token, loginData.refreshToken);
      // clear and reload favorites for the newly registered (and logged-in) user
      try {
        const { clearFavorites, loadFavorites } = useFavoritesStore.getState();
        clearFavorites();
        loadFavorites().catch((e) => console.error('Failed to load favorites after signup', e));
      } catch (e) {
        console.error('Favorites store not available', e);
      }

      localStorage.setItem("userEmail", email);
      localStorage.setItem("userNickname", userNickname);

      navigate("/");
    } catch (err) {
      const message = err instanceof Error ? err.message : "An error occurred while signing up.";
      setError(message);
    }
  };

  return (
    <>
      <PageBackground variant="signup" />
      <div className="w-full flex flex-col flex-1 relative z-10">
        <PageTitle title="Sign Up" />
        <div className="flex-1 flex flex-col justify-center w-full pb-20 px-4 md:px-0">
          <form onSubmit={handleSubmit} className="flex flex-col items-center justify-center auth-form w-full">
            <div className="flex flex-col justify-center items-center gap-[24px] w-full">
              <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
                <label className="text-[#FFFBE4] text-[24px]">Nickname</label>
                <input
                  type="text"
                  placeholder="Nickname"
                  value={userNickname}
                  onChange={(e) => setUserNickname(e.target.value)}
                  className="auth-form__input w-full"
                  required
                />
              </div>

              <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
                <label className="text-[#FFFBE4] text-[24px]">E-mail</label>
                <input
                  type="email"
                  placeholder="E-mail"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="auth-form__input w-full"
                  required
                />
              </div>

              <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
                <label className="text-[#FFFBE4] text-[24px]">Password</label>
                <input
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="auth-form__input w-full"
                  required
                />
              </div>

              <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
                <label className="text-[#FFFBE4] text-[24px]">Repeat Password</label>
                <input
                  type="password"
                  placeholder="Repeat Password"
                  value={repeatPassword}
                  onChange={(e) => setRepeatPassword(e.target.value)}
                  className="auth-form__input w-full"
                  required
                />
              </div>

              <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
                <label className="text-[#FFFBE4] text-[24px]">Avatar (optional)</label>
                <label className="auth-form__file w-full">
                  <span className="auth-form__file-text">
                    {avatarFile?.name || "Choose avatar file"}
                  </span>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => setAvatarFile(e.target.files ? e.target.files[0] : null)}
                    className="auth-form__file-input"
                  />
                </label>
              </div>
            </div>

            {error && (
              <div className="text-red-500 mt-4 text-center w-full md:w-[450px]">
                {error}
              </div>
            )}

            <Button type="submit" className="w-full md:w-[450px] mt-[56px]">
              Sign Up
            </Button>
          </form>
        </div>
      </div>
    </>
  );
};

