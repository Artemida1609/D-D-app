import { PageTitle } from "../../shared/ui/PageTitle";
import "../../shared/styles/AuthForm.scss";
import { Form } from "../../shared/ui/Form/Form";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";

export const LoginPage = () => {
  return (
    <>
      <PageBackground variant="login" />
      <div className="w-full flex flex-col flex-1 relative z-10">
        <PageTitle title="Log In" />
        <Form type="login" />
      </div>
    </>
  );
};
