import { MainLayout } from "../../app/layouts/MainLayout";

export const HomePage = () => {
  return (
    <MainLayout>
      <div className="relative h-full w-full flex flex-col justify-center">
        {/* background */}
        <video
          autoPlay
          muted
          loop
          className="absolute inset-0 w-full h-full object-cover"
        >
          <source src="/videos/hero-bg-1.mp4" type="video/mp4" />
        </video>

        {/* overlay */}
        <div className="absolute inset-0 bg-black/40" />

        {/* content */}
        <div className="relative flex flex-col ml-24">
          <h1 className="text-white text-5xl font-black uppercase tracking-wide mb-2">
            Dungeons & Dragons
          </h1>
          <p className="text-[#C4CBCE] text-lg mb-8">
            Welcome to the ultimate Dungeons & Dragons wiki!
          </p>
          <button className="bg-red-500 text-white text-xl font-bold px-6 py-2 rounded-md hover:bg-red-600 transition-colors w-96">
            Get Started
          </button>
        </div>
      </div>
    </MainLayout>
  );
};
