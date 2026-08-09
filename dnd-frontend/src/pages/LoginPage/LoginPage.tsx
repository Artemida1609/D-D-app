import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { PageTitle } from "../../shared/ui/PageTitle";
import "../../shared/styles/AuthForm.scss";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";
import { Button } from "../../shared/ui/Button/Button";
import { useAuthStore } from "../../shared/store/authStore";
import { useFavoritesStore } from "../../shared/store/favoritesStore";
import { API_BASE_URL } from "../../shared/api/config";

export const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const login = useAuthStore((state) => state.login);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      const data = (await response.json().catch(() => null)) as
        | { token?: string; refreshToken?: string; message?: string; error?: string }
        | null;
      if (!response.ok) {
        throw new Error(data?.message || data?.error || "Login failed");
      }

      if (data?.token) {
        const previousEmail = localStorage.getItem("userEmail");
        const storedNickname = localStorage.getItem("userNickname");
        login(data.token, data.refreshToken);
        // clear and reload favorites for the newly logged-in user
        try {
          const { clearFavorites, loadFavorites } = useFavoritesStore.getState();
          clearFavorites();
          // loadFavorites may be async — call but don't block UI excessively
          loadFavorites().catch((e) => console.error('Failed to load favorites after login', e));
        } catch (e) {
          console.error('Favorites store not available', e);
        }

        if (email) {
          localStorage.setItem("userEmail", email);
          if (!storedNickname || previousEmail !== email) {
            localStorage.setItem("userNickname", email.split("@")[0]);
          }
        }
        navigate("/");
      }
    } catch (err) {
      const message = err instanceof Error ? err.message : "An error occurred while logging in.";
      setError(message);
    }
  };

  return (
    <>
      <PageBackground variant="login" />
      <div className="w-full flex flex-col flex-1 relative z-10">
        <PageTitle title="Log In" />
        <div className="flex-1 flex flex-col justify-center w-full pb-20 px-4 md:px-0">
          <form onSubmit={handleSubmit} className="flex flex-col items-center justify-center auth-form w-full">
            <div className="flex flex-col justify-center items-center gap-[24px] w-full">
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
            </div>

            {error && (
              <div className="text-red-500 mt-4 text-center w-full md:w-[450px]">
                {error}
              </div>
            )}

            <Button type="submit" className="w-full md:w-[450px] mt-[56px]">
              Log In
            </Button>
          </form>
        </div>
      </div>
    </>
  );
};

