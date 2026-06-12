import { Button } from "../../shared/ui/Button/Button";
import "./HomePage.scss";

export const HomePage = () => {
  return (
    <>
      <div
        className="fixed top-0 left-0 w-full h-full bg-cover bg-center bg-no-repeat -z-10 home-page__bg"
      />
      <section className="flex-1 flex flex-col items-center justify-center gap-12 w-full pt-[10vh]">
        <h1 className="flex text-center align-center justify-center home-page__title">
          Everything Dungeons & Dragons.
          <br /> One place.
        </h1>
        <Button className="home-page__start-exploring-btn w-full md:w-[380px]">
          Start Exploring
        </Button>
      </section>
    </>
  );
};
