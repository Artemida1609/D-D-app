import { Form } from "../../shared/ui/Form/Form";
import { PageTitle } from "../../shared/ui/PageTitle";

export const AccountPage = () => {
  return (
    <div className="w-full flex flex-col flex-1 pb-20">
      <div className="fixed top-0 left-0 w-full h-full bg-[#00192D] -z-10" />

      <PageTitle title="Account" />

      <Form type="account" />
    </div>
  );
};
