import "./Button.scss";

export const Button = ({ children, className = "", onClick, type = "button" }: any) => {
  return (
    <button type={type} className={`primary-button ${className}`} onClick={onClick}>
      {children}
    </button>
  );
};
