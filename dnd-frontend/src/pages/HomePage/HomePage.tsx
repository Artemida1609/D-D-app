import "./HomePage.scss";

export const HomePage = () => {
  return (
    <>
      <div
        className="fixed top-0 left-0 w-full h-full bg-cover bg-center bg-no-repeat -z-10"
        style={{ backgroundImage: 'url("/images/bg/hero-bg.webp")' }}
      />
      <section className="flex-1 flex flex-col items-center justify-center gap-12 w-full pt-[10vh]">
        <h1 className="flex text-center align-center justify-center home-page__title">
          Everything Dungeons & Dragons.<br /> One place.
        </h1>
        <button className="w-[380px] h-[64px] rounded-[25px] home-page__start-exploring-btn">
          Start Exploring
        </button>
      </section>
    </>
  );
};
