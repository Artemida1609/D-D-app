import { PageTitle } from "../../shared/ui/PageTitle";
// import { Button } from "../../shared/ui/Button/Button";
import "../../shared/styles/AuthForm.scss";
import { Form } from "../../shared/ui/Form/Form";

export const SignUpPage = () => {
  return (
    <>
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />
      <div className="w-full flex flex-col flex-1">
        <PageTitle title="Sign Up" />

        <Form type="signup" />
      </div>
    </>
  );
};
