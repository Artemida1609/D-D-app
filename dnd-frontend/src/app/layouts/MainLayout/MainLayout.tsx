import { Header } from "../../../shared/ui/Header/Header";
import { Footer } from "../../../shared/ui/Footer/Footer";
import { Outlet } from "react-router-dom";
import { useEffect, useState, useRef } from "react";
import { SideBar } from "../../../shared/ui/SideBar/SideBar";

export const MainLayout = () => {
  const [activeAside, setActiveAside] = useState(false);

  const prevOverflow = useRef<string | null>(null);

  useEffect(() => {
    if (activeAside) {
      // save previous overflow and disable scrolling
      prevOverflow.current = document.body.style.overflow;
      document.body.style.overflow = "hidden";
    } else {
      // restore previous overflow (or empty string)
      document.body.style.overflow = prevOverflow.current ?? "";
      prevOverflow.current = null;
    }

    return () => {
      // cleanup on unmount
      document.body.style.overflow = prevOverflow.current ?? "";
    };
  }, [activeAside]);

  return (
    <>
      <div className="flex flex-col min-h-screen w-full max-w-[1280px] mx-auto main-layout">
        <Header setActiveAside={setActiveAside} />
        {activeAside && <SideBar setActiveAside={setActiveAside} />}
        <main className="flex-1 w-full flex flex-col">
          <Outlet />
        </main>
        <Footer />
      </div>
    </>
  );
};
