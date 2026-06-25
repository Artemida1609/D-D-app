import { useAuthStore } from "../../store/authStore";

export const DevAuthToggle = () => {
  const { isLoggedIn, toggleAuth } = useAuthStore();

  return (
    <button
      onClick={toggleAuth}
      style={{
        position: "fixed",
        bottom: "0",
        right: "0",
        zIndex: 99999,
        width: "30px",
        height: "30px",
        border: "none",
        cursor: "pointer",
        backgroundColor: isLoggedIn ? "green" : "red",
      }}
    />
  );
};
