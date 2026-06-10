import { PageTitle } from "../../shared/ui/PageTitle";
import "./SignUpPage.scss";

export const SignUpPage = () => {
  return (
    <>
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />
      <div className="w-full flex flex-col flex-1">
        <PageTitle title="Sign Up" />

        <div className="flex-1 flex w-full justify-center pb-20">
          <form className="flex flex-col items-center sign-up">
            <div className="flex flex-col gap-[24px] w-full">
              <div className="flex flex-col gap-[8px]">
                <label className="text-white text-[24px]">Name</label>
                <input type="text" placeholder="Name" className="sign-up__input" />
              </div>

              <div className="flex flex-col gap-[8px]">
                <label className="text-white text-[24px]">E-mail</label>
                <input type="email" placeholder="E-mail" className="sign-up__input" />
              </div>

              <div className="flex flex-col gap-[8px]">
                <label className="text-white text-[24px]">Password</label>
                <input type="password" placeholder="Password" className="sign-up__input" />
              </div>

              <div className="flex flex-col gap-[8px]">
                <label className="text-white text-[24px]">Repeat Password</label>
                <input type="password" placeholder="Repeat Password" className="sign-up__input" />
              </div>
            </div>

            <button type="submit" className="sign-up__button mt-[56px]">
              Sign Up
            </button>
          </form>
        </div>
      </div>
    </>
  );
};
