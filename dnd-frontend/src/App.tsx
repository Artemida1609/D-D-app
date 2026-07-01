import { BrowserRouter, Routes, Route } from "react-router-dom";
import { MainLayout } from "./app/layouts/MainLayout/MainLayout";
import { HomePage } from "./pages/HomePage/HomePage";
import { SignUpPage } from "./pages/SignUpPage/SignUpPage";
import { LoginPage } from "./pages/LoginPage/LoginPage";
import { CharacterPage } from "./pages/CharacterPage/CharacterPage";
import { EquipmentPage } from "./pages/EquipmentPage/EquipmentPage";
import { MagicPage } from "./pages/MagicPage/MagicPage";
import { AsyncCategoryPage } from "./pages/AsyncCategoryPage/AsyncCategoryPage";
import { DetailPage } from "./pages/DetailPage/DetailPage";
import { DevAuthToggle } from "./shared/ui/DevAuthToggle/DevAuthToggle";
import { FavoritesPage } from "./pages/FavoritesPage/FavoritesPage";
import { AccountPage } from "./pages/AccountPage/AccountPage";
import { ClassesListPage } from "./pages/ClassesListPage/ClassesListPage";
import { ClassDetailPage } from "./pages/ClassDetailPage/ClassDetailPage";

export function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* MAIN LAYOUT */}
        <Route path="/" element={<MainLayout />}>
          <Route index element={<HomePage />} />

          {/* CHARACTER PAGE */}
          <Route path="character">
            <Route index element={<CharacterPage />} />

            <Route path="species">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Species" 
                    endpoint="/api/races" 
                    basePath="/character/species" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>

            <Route path="classes">
              <Route index element={<ClassesListPage />} />
              <Route path=":index" element={<ClassDetailPage />} />
            </Route>

            <Route path="skills">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Skills" 
                    endpoint="/api/skills" 
                    basePath="/character/skills" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* EQUIPMENT PAGE */}
          <Route path="equipment">
            <Route index element={<EquipmentPage />} />

            <Route path="weapons">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Weapons" 
                    endpoint="/api/equipment" 
                    basePath="/equipment/weapons" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>

            <Route path="armors">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Armors" 
                    endpoint="/api/equipment" 
                    basePath="/equipment/armors" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>

            <Route path="gear">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Gear" 
                    endpoint="/api/equipment" 
                    basePath="/equipment/gear" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* MAGIC PAGE */}
          <Route path="magic">
            <Route index element={<MagicPage />} />

            <Route path="spells">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Spells" 
                    endpoint="/api/spells" 
                    basePath="/magic/spells" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>

            <Route path="schools">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Schools" 
                    endpoint="/api/magic-schools" 
                    basePath="/magic/schools" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* BESTIARY PAGE */}
          <Route path="bestiary">
            <Route
              index
              element={
                <AsyncCategoryPage
                  title="Bestiary"
                  endpoint="/api/monsters"
                  basePath="/bestiary/monster"
                  backgroundVariant="bestiary"
                />
              }
            />

            <Route path="monster">
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* OTHER PAGES */}
          <Route path="login" element={<LoginPage />} />
          <Route path="signup" element={<SignUpPage />} />
          <Route path="favorites" element={<FavoritesPage />} />
          <Route path="account" element={<AccountPage />} />
          <Route path="*" element={<></>} />
        </Route>
      </Routes>

      {/* !!! DEV !!! */}
      <DevAuthToggle />
    </BrowserRouter>
  );
}
