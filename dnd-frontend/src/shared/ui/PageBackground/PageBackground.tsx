import "./PageBackground.scss";

type BackgroundVariant = "signup" | "login" | "account" | "bestiary" | "favorites";

interface PageBackgroundProps {
  variant: BackgroundVariant;
}

export const PageBackground = ({ variant }: PageBackgroundProps) => {
  return <div className={`page-background page-background--${variant}`} />;
};
