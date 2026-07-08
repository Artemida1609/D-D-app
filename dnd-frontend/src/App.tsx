import { BrowserRouter, Routes, Route } from "react-router-dom";
import { MainLayout } from "./app/layouts/MainLayout/MainLayout";
import { HomePage } from "./pages/HomePage/HomePage";
import { SignUpPage } from "./pages/SignUpPage/SignUpPage";
import { LoginPage } from "./pages/LoginPage/LoginPage";
import { AsyncCategoryPage } from "./pages/AsyncCategoryPage/AsyncCategoryPage";
import { DetailPage } from "./pages/DetailPage/DetailPage";
import { DevAuthToggle } from "./shared/ui/DevAuthToggle/DevAuthToggle";
import { FavoritesPage } from "./pages/FavoritesPage/FavoritesPage";
import { AccountPage } from "./pages/AccountPage/AccountPage";

export function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* MAIN LAYOUT */}
        <Route path="/" element={<MainLayout />}>
          <Route index element={<HomePage />} />

          {/* CHARACTER PAGE */}
          <Route path="character">
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
              <Route 
                index 
                element={
                  <AsyncCategoryPage 
                    title="Classes" 
                    endpoint="/api/classes" 
                    basePath="/character/classes" 
                  />
                } 
              />
              <Route path=":id" element={<DetailPage />} />
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
            <Route path="weapons">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title="Weapons" 
                    endpoint="/api/equipment-categories/weapon" 
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
                    endpoint="/api/equipment-categories/armor" 
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
                    endpoint="/api/equipment-categories/gear" 
                    basePath="/equipment/gear" 
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* MAGIC PAGE */}
          <Route path="magic">
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
