import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { MainLayout } from './app/layouts/MainLayout';
import { HomePage } from './pages/HomePage/HomePage';
import { ClassesPage } from './pages/ClassesPage/ClassesPage';

export function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route 
          path="/" 
          element={
            <MainLayout>
              <HomePage />
            </MainLayout>
          } 
        />
        <Route 
          path="/classes" 
          element={
            <MainLayout>
              <ClassesPage />
            </MainLayout>
          } 
        />
      </Routes>
    </BrowserRouter>
  );
}
