import { PageTitle } from "../../shared/ui/PageTitle";
import { Button } from "../../shared/ui/Button/Button";
import "../../shared/styles/AuthForm.scss";

export const LoginPage = () => {
  return (
    <>
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />
      <div className="w-full flex flex-col flex-1">
        <PageTitle title="Log In" />

        <div className="flex-1 flex w-full justify-center pb-20">
          <form className="flex flex-col items-center auth-form">
            <div className="flex flex-col gap-[24px] w-full">
              <div className="flex flex-col gap-[8px]">
                <label className="text-white text-[24px]">E-mail</label>
                <input type="email" placeholder="E-mail" className="auth-form__input" />
              </div>

              <div className="flex flex-col gap-[8px]">
                <label className="text-white text-[24px]">Password</label>
                <input type="password" placeholder="Password" className="auth-form__input" />
              </div>
            </div>

            <Button type="submit" className="w-full md:w-[450px] mt-[56px]">
              Log In
            </Button>
          </form>
        </div>
      </div>
    </>
  );
};
