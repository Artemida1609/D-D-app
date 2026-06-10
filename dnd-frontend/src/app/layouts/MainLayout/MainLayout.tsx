import { Header } from "../../../shared/ui/Header/Header";
import { Footer } from "../../../shared/ui/Footer/Footer";
import { Outlet } from "react-router-dom";
import { useState } from "react";
import { SideBar } from "../../../shared/ui/SideBar/SideBar";

export const MainLayout = () => {
  const [activeAside, setActiveAside] = useState(false);

  return (
    <>
      <div className="flex flex-col min-h-screen w-full max-w-[1280px] relative main-layout">
        <Header setActiveAside={setActiveAside} />
        {activeAside && <SideBar setActiveAside={setActiveAside} />}
        <main className="flex-1 w-full flex flex-col mx-auto px-6 xl:px-0">
          <Outlet />
        </main>
        <Footer />
      </div>
    </>
  );
};
