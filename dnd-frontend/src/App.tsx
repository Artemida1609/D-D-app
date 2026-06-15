import { BrowserRouter, Routes, Route } from "react-router-dom";
import { MainLayout } from "./app/layouts/MainLayout/MainLayout";
import { HomePage } from "./pages/HomePage/HomePage";
import { SignUpPage } from "./pages/SignUpPage/SignUpPage";
import { LoginPage } from "./pages/LoginPage/LoginPage";
import { CharacterPage } from "./pages/CharacterPage/CharacterPage";
import { EquipmentPage } from "./pages/EquipmentPage/EquipmentPage";
import { MagicPage } from "./pages/MagicPage/MagicPage";
import { CategoryListPage } from "./pages/CategoryListPage/CategoryListPage";
import { DetailPage } from "./pages/DetailPage/DetailPage";
import { DevAuthToggle } from "./shared/ui/DevAuthToggle/DevAuthToggle";
import { FavoritesPage } from "./pages/FavoritesPage/FavoritesPage";
import {
  mockSpecies,
  mockClasses,
  mockSkills,
  mockWeapons,
  mockArmors,
  mockGear,
  mockSpells,
  mockSchools,
  mockBestiary
} from "./shared/constants/mockData";
import { AccountPage } from "./pages/AccountPage/AccountPage";

export function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* MAIN LAYOUT використовує Outlet для вкладень */}
        <Route path="/" element={<MainLayout />}>
          <Route index element={<HomePage />} />

          {/* CHARACTER PAGE */}
          <Route path="character">
            <Route index element={<CharacterPage />} />
            
            <Route path="species">
              <Route index element={<CategoryListPage title="Species" items={mockSpecies} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
            
            <Route path="classes">
              <Route index element={<CategoryListPage title="Classes" items={mockClasses} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
            
            <Route path="skills">
              <Route index element={<CategoryListPage title="Skills" items={mockSkills} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* EQUIPMENT PAGE */}
          <Route path="equipment">
            <Route index element={<EquipmentPage />} />
            
            <Route path="weapons">
              <Route index element={<CategoryListPage title="Weapons" items={mockWeapons} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
            
            <Route path="armors">
              <Route index element={<CategoryListPage title="Armors" items={mockArmors} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
            
            <Route path="gear">
              <Route index element={<CategoryListPage title="Gear" items={mockGear} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* MAGIC PAGE */}
          <Route path="magic">
            <Route index element={<MagicPage />} />
            
            <Route path="spells">
              <Route index element={<CategoryListPage title="Spells" items={mockSpells} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
            
            <Route path="schools">
              <Route index element={<CategoryListPage title="Schools" items={mockSchools} />} />
              <Route path=":id" element={<DetailPage />} />
            </Route>
          </Route>

          {/* BESTIARY PAGE */}
          <Route path="bestiary">
            <Route index element={<CategoryListPage title="Bestiary" items={mockBestiary} backgroundVariant="bestiary" />} />
            
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
