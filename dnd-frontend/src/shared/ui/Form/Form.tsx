import { Button } from "../Button/Button";

export const Form = ({ type }: { type: "signup" | "login" | "account" }) => {
  return (
    <div className="flex-1 flex flex-col justify-center w-full pb-20 px-4 md:px-0">
      {type === "account" && (
        <div className="flex justify-center mb-[24px] w-full h-[170px]">
          <img
            src="/images/account_girl.jpg"
            alt="Account Girl"
            loading="lazy"
            decoding="async"
            fetchPriority="low"
            width={170}
            height={170}
            className="w-[170px] h-full object-cover rounded-full"
          />
        </div>
      )}
      <form className="flex flex-col items-center justify-center auth-form w-full">
        <div className="flex flex-col justify-center items-center gap-[24px] w-full">
          <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
            <label className="text-white text-[24px]">Name</label>
            <input
              type="text"
              placeholder="Name"
              className="auth-form__input w-full"
            />
          </div>

          <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
            <label className="text-white text-[24px]">E-mail</label>
            <input
              type="email"
              placeholder="E-mail"
              className="auth-form__input w-full"
            />
          </div>

          <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
            <label className="text-white text-[24px]">Password</label>
            <input
              type="password"
              placeholder="Password"
              className="auth-form__input w-full"
            />
          </div>
          {type === "signup" && (
            <div className="flex flex-col gap-[8px] w-full md:w-[450px]">
              <label className="text-white text-[24px]">Repeat Password</label>
              <input
                type="password"
                placeholder="Repeat Password"
                className="auth-form__input w-full"
              />
            </div>
          )}
        </div>

        {type === "login" && (
          <Button type="submit" className="w-full md:w-[450px] mt-[56px]">
            Log In
          </Button>
        )}
        {type === "signup" && (
          <Button type="submit" className="w-full md:w-[450px] mt-[56px]">
            Sign Up
          </Button>
        )}

        {type === "account" && (
          <>
            <Button
              type="submit"
              className="w-full md:w-[450px] mt-[56px] primary-button__login"
            >
              Log Out
            </Button>
            <Button
              type="submit"
              className="w-full md:w-[450px] mt-[24px] primary-button__delete"
            >
              Delete Account
            </Button>
          </>
        )}
      </form>
    </div>
  );
};
