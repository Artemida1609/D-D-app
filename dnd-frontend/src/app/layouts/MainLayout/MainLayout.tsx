import { Header } from "../../../shared/ui/Header/Header";
import { Footer } from "../../../shared/ui/Footer/Footer";
import { Outlet } from "react-router-dom";
import { useEffect, useState } from "react";
import { SideBar } from "../../../shared/ui/SideBar/SideBar";

export const MainLayout = () => {
  const [activeAside, setActiveAside] = useState(false);

  useEffect(() => {
    if (activeAside) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "visible";
    }
  }, [activeAside]);

  return (
    <>
      <div className="flex flex-col min-h-screen w-full max-w-[1280px] mx-auto main-layout">
        <Header setActiveAside={setActiveAside} />
        {activeAside && <SideBar setActiveAside={setActiveAside} />}
        <main className="flex-1 w-full flex flex-col px-6">
          <Outlet />
        </main>
        <Footer />
      </div>
    </>
  );
};
