import { Header } from "../../../shared/ui/Header/Header";
import { Footer } from "../../../shared/ui/Footer/Footer";
import { Outlet } from "react-router-dom";
import { useEffect, useState, useRef } from "react";
import { SideBar } from "../../../shared/ui/SideBar/SideBar";
import { useAuthStore } from "../../../shared/store/authStore";
import { useFavoritesStore } from "../../../shared/store/favoritesStore";
import "./MainLayout.scss";

export const MainLayout = () => {
  const [activeAside, setActiveAside] = useState(false);
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);

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

  useEffect(() => {
    if (isLoggedIn) {
      void useFavoritesStore.getState().loadFavorites();
    }
  }, [isLoggedIn]);

  return (
    <>
      <div className="flex flex-col min-h-screen w-full mx-auto main-layout">
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
