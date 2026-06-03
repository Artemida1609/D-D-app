import { useEffect, useRef, useState } from "react";
import { MainLayout } from "../../app/layouts/MainLayout";
import "./HomePage.scss";

export const HomePage = () => {
  const sectionDiscRef = useRef<HTMLElement>(null);
  const sectionNewsRef = useRef<HTMLElement>(null);
  const [showDiscoverBg, setShowDiscoverBg] = useState(false);
  const [showNewsBg, setShowNewsBg] = useState(false);

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          const isVisible = entry.intersectionRatio >= 0.5;
          if (entry.target === sectionDiscRef.current)
            setShowDiscoverBg(isVisible);
          if (entry.target === sectionNewsRef.current) setShowNewsBg(isVisible);
        });
      },
      { threshold: [0, 0.1, 0.2, 0.3, 0.4, 0.5] },
    );

    if (sectionDiscRef.current) observer.observe(sectionDiscRef.current);
    if (sectionNewsRef.current) observer.observe(sectionNewsRef.current);

    return () => observer.disconnect();
  }, []);

  return (
    <MainLayout>
      {/* Відео — завжди видиме, ховається коли з'являється картинка */}
      <video
        autoPlay
        muted
        loop
        className="fixed inset-0 w-full h-full object-cover"
        style={{ zIndex: -2 }}
      >
        <source src="/videos/hero-bg-1.mp4" type="video/mp4" />
      </video>

      {/* Картинка — поверх відео, з'являється при скролі */}
      <div
        className="fixed inset-0 w-full h-full"
        style={{
          zIndex: -1,
          opacity: showDiscoverBg ? 1 : 0,
          transition: "opacity 0.7s ease-in-out",
        }}
      >
        <img
          src="/images/bg/discover-bg.jpg"
          alt="discover bg"
          className="w-full h-full object-cover"
        />
        <div className="absolute inset-0 bg-black/30" />
      </div>
      {/* Картинка — поверх відео, з'являється при скролі */}
      <div
        className="fixed inset-0 w-full h-full"
        style={{
          zIndex: -1,
          opacity: showNewsBg ? 1 : 0,
          transition: "opacity 0.7s ease-in-out",
        }}
      >
        <img
          src="/images/bg/news-bg.png"
          alt="news bg"
          className="w-full h-full object-cover"
        />
        {/* <div className="absolute inset-0 bg-black/0" /> */}
      </div>

      <section
        className="relative h-screen w-full flex flex-col justify-center"
        id="hero"
      >
        <div className="absolute inset-0 bg-black/30" />
        <div className="relative flex flex-col ml-24">
          <div className="relative flex flex-col items-start">
            <img
              src="/icons/main-logo-1.png"
              alt="D&D Logo"
              className="w-96 h-auto"
            />
            <p className="absolute bottom-16 text-[#C4CBCE] text-lg mb-8">
              Welcome to the ultimate Dungeons & Dragons wiki!
            </p>
            <button className="absolute bottom-0 bg-red-500 text-white text-xl font-bold px-6 py-2 rounded-md hover:bg-red-600 transition-colors w-96 cursor-pointer">
              Get Started
            </button>
          </div>
        </div>
      </section>

      <section
        ref={sectionDiscRef}
        className="relative h-screen w-full flex flex-col justify-center"
        id="discover"
      >
        <div className="absolute inset-0 bg-black/30" />
        <div className="relative flex flex-col text-center">
          <DiscoverText />
          <p className="text-[#C4CBCE] text-3xl uppercase ml-24 max-w-2xl">
            The thrill of adventure
          </p>
        </div>
      </section>

      <section
        ref={sectionNewsRef}
        className="relative h-screen w-full flex flex-col justify-center"
        id="news"
      >
        <div className="absolute inset-0 bg-black/30" />
        <div className="relative flex flex-col text-center">
          {/* <h2 className="text-5xl font-bold text-green-500 uppercase tracking-wide">
              Latest News
            </h2> */}
          <NewsText />
          <p className="text-[#C4CBCE] text-2xl uppercase ml-24 max-w-2xl mt-4">
            Stay updated with the latest D&D news and releases
          </p>
        </div>
      </section>
    </MainLayout>
  );
};

export const DiscoverText = () => {
  return (
    <svg
      viewBox="0 0 900 150"
      className="w-full max-w-4xl"
      xmlns="http://www.w3.org/2000/svg"
    >
      <defs>
        <linearGradient id="textGrad" x1="0" y1="0" x2="0" y2="1">
          <stop offset="0%" stopColor="#ffaa30" />
          <stop offset="45%" stopColor="#e06010" />
          <stop offset="100%" stopColor="#c03000" />
        </linearGradient>
      </defs>
      <text
        x="50%"
        y="120"
        textAnchor="middle"
        fontFamily="'Cinzel Decorative', serif"
        fontWeight="900"
        fontSize="80"
        fill="none"
        stroke="#3a0e00"
        strokeWidth="14"
        strokeLinejoin="round"
      >
        DISCOVER
      </text>
      <text
        x="50%"
        y="120"
        textAnchor="middle"
        fontFamily="'Cinzel Decorative', serif"
        fontWeight="900"
        fontSize="80"
        fill="url(#textGrad)"
      >
        DISCOVER
      </text>
    </svg>
  );
};

export const NewsText = () => {
  return (
    <svg
      viewBox="0 0 600 150"
      className="w-full max-w-2xl"
      xmlns="http://www.w3.org/2000/svg"
    >
      <defs>
        <linearGradient id="newsGrad" x1="0" y1="0" x2="0" y2="1">
          <stop offset="0%" stopColor="#c8f060" />
          <stop offset="50%" stopColor="#7ab820" />
          <stop offset="100%" stopColor="#4a7a00" />
        </linearGradient>
      </defs>

      {/* Обводка */}
      <text
        x="50%"
        y="120"
        textAnchor="middle"
        // fontFamily="'Henny Penny', cursive"
        // fontFamily="'New Rocker', cursive"
        fontFamily="'Cinzel Decorative', cursive"
        fontWeight="900"
        fontSize="110"
        fill="none"
        stroke="#1a3300"
        strokeWidth="16"
        strokeLinejoin="round"
      >
        NEWS
      </text>

      {/* Градієнт */}
      <text
        x="50%"
        y="120"
        textAnchor="middle"
        // fontFamily="'Henny Penny', cursive"
        // fontFamily="'New Rocker', cursive"
        fontFamily="'Cinzel Decorative', cursive"
        fontWeight="900"
        fontSize="110"
        fill="url(#newsGrad)"
      >
        NEWS
      </text>
    </svg>
  );
};
