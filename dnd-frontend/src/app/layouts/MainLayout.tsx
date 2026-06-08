import { Header } from "../../shared/ui/Header/Header";
import { Outlet } from "react-router-dom";


export const MainLayout = () => {
  return (
    <div className="h-screen flex flex-col">
      <Header />
      <main className="flex-1 min-h-0">
        <Outlet />
      </main>
      {/* <Footer /> */}
    </div>
  );
};
