import { Form } from "../../shared/ui/Form/Form";
import { PageTitle } from "../../shared/ui/PageTitle";
import { PageBackground } from "../../shared/ui/PageBackground/PageBackground";

export const AccountPage = () => {
  return (
    <>
      <PageBackground variant="account" />
      <div className="w-full flex flex-col flex-1 pb-20 relative z-10">
        <PageTitle title="Account" />
        <Form type="account" />
      </div>
    </>
  );
};
