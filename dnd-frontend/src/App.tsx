import { useTranslation } from "react-i18next";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { MainLayout } from "./app/layouts/MainLayout/MainLayout";
import { HomePage } from "./pages/HomePage/HomePage";
import { SignUpPage } from "./pages/SignUpPage/SignUpPage";
import { LoginPage } from "./pages/LoginPage/LoginPage";
import { AsyncCategoryPage } from "./pages/AsyncCategoryPage/AsyncCategoryPage";
import { EquipmentCategoryPage } from "./pages/EquipmentCategoryPage/EquipmentCategoryPage";
import { DetailPage } from "./pages/DetailPage/DetailPage";
import { FavoritesPage } from "./pages/FavoritesPage/FavoritesPage";
import { AccountPage } from "./pages/AccountPage/AccountPage";

export function App() {
  const { t } = useTranslation();
  return (
    <BrowserRouter>
      <Routes>
        {/* MAIN LAYOUT */}
        <Route path="/" element={<MainLayout />}>
          <Route index element={<HomePage />} />

          {/* CHARACTER PAGE */}
          <Route path="character">
            <Route index element={<Navigate replace to="species" />} />
            <Route path="species">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title={t("nav.species")} 
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
                    title={t("nav.classes")} 
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
                    title={t("nav.skills")} 
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
            <Route index element={<Navigate replace to="weapons" />} />
            <Route path="weapons">
              <Route
                index
                element={
                  <EquipmentCategoryPage 
                    title={t("nav.weapons")} 
                    endpoint="/api/equipments" 
                    categoriesEndpoint="/api/equipment-categories/weapon"
                    basePath="/equipment/weapons" 
                    equipmentType="weaponCategory"
                  />
                } 
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>

            <Route path="armors">
              <Route
                index
                element={
                  <EquipmentCategoryPage 
                    title={t("nav.armor")} 
                    endpoint="/api/equipments" 
                    categoriesEndpoint="/api/equipment-categories/armor"
                    basePath="/equipment/armors" 
                    equipmentType="armorCategory"
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>

            <Route path="gear">
              <Route
                index
                element={
                  <EquipmentCategoryPage 
                    title={t("nav.gear")} 
                    endpoint="/api/equipments" 
                    categoriesEndpoint="/api/equipment-categories/gear"
                    basePath="/equipment/gear" 
                    equipmentType="gearCategory"
                  />
                }
              />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* MAGIC PAGE */}
          <Route path="magic">
            <Route index element={<Navigate replace to="spells" />} />
            <Route path="spells">
              <Route
                index
                element={
                  <AsyncCategoryPage 
                    title={t("nav.spells")} 
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
                    title={t("nav.schools")} 
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
                  title={t("nav.bestiary")}
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
          <Route path="*" element={<Navigate replace to="/" />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
