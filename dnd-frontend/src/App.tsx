import { BrowserRouter, Routes, Route } from "react-router-dom";
import { MainLayout } from "./app/layouts/MainLayout";
import { HomePage } from "./pages/HomePage/HomePage";
import { ClassesPage } from "./pages/ClassesPage/ClassesPage";
import { SignUpPage } from "./pages/SignUpPage/SignUpPage";

export function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* MAIN LAYOUT використовує Outlet для вкладень */}
        <Route path="/" element={<MainLayout />}>
          <Route index element={<HomePage />} />

          {/* CHARACTER PAGE */}
          <Route path="character" element={<></>}>
            <Route path="species" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
            <Route path="classes" element={<ClassesPage />}>
              <Route path=":id" element={<></>} />
            </Route>
            <Route path="skills" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
          </Route>

          {/* EQUIPMENT PAGE */}
          <Route path="equipment" element={<></>}>
            <Route path="weapons" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
            <Route path="armors" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
            <Route path="gear" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
          </Route>

          {/* MAGIC PAGE */}
          <Route path="magic" element={<></>}>
            <Route path="spells" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
            <Route path="schools" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
          </Route>

          {/* BESTIARY PAGE */}
          <Route path="bestiary" element={<></>}>
            <Route path="monster" element={<></>}>
              <Route path=":id" element={<></>} />
            </Route>
          </Route>

          {/* OTHER PAGES */}
          <Route path="signup" element={<SignUpPage />} />
          <Route path="favorites" element={<></>} />
          <Route path="account" element={<></>} />
          <Route path="*" element={<></>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
