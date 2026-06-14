import React from "react";
import "./Button.scss";

type ButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  children: React.ReactNode;
  className?: string;
};

export const Button: React.FC<ButtonProps> = ({ children, className = "", ...props }) => {
  return (
    <button className={`primary-button ${className}`} {...props}>
      {children}
    </button>
  );
};
