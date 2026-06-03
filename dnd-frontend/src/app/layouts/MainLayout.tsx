import type { ReactNode } from "react";
import { Header } from "../../shared/ui/Header/Header";

interface MainLayoutProps {
  children: ReactNode;
}

export const MainLayout = ({ children }: MainLayoutProps) => {
  return (
    <div className="h-screen flex flex-col">
      <Header />
      <main className="flex-1 min-h-0">{children}</main>
      {/* <Footer /> */}
    </div>
  );
};
