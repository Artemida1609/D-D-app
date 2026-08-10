import "./Footer.scss";

export const Footer = () => {
  return (
    <footer className="w-full px-5 md:px-20 pb-[34px] md:pb-[40px] pt-10 z-10">
      <div className="flex flex-col justify-center items-center gap-4 text-center">
        <p className="footer__text">
          Educational project created at Mate Academy. Dungeons & Dragons and related IP belong to Wizards of the Coast. For informational purposes only.
        </p>
        
        <a 
          href="https://github.com/Artemida1609/D-D-app" 
          target="_blank" 
          rel="noreferrer"
          className="footer__link"
        >
          <img 
            src="/images/icons/github.jpg" 
            alt="GitHub logo" 
            className="footer__icon"
          />
          https://github.com/Artemida1609/D-D-app
        </a>
      </div>
    </footer>
  );
};
