import React from "react";
import "./Button.scss";

type ButtonProps = {
  children: React.ReactNode;
  className?: string;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
  type?: 'button' | 'submit' | 'reset';
};

export const Button: React.FC<ButtonProps> = ({ children, className = "", onClick, type = "button" }) => {
  return (
    <button type={type} className={`primary-button ${className}`} onClick={onClick}>
      {children}
    </button>
  );
};
