const DND_IMAGE_HOST = "www.dnd5eapi.co";

const isRemoteDndImage = (imageUrl: string): boolean => {
  try {
    const parsed = new URL(imageUrl);
    return parsed.hostname === DND_IMAGE_HOST && parsed.pathname.startsWith("/api/images/");
  } catch {
    return false;
  }
};

export const getOptimizedCardImageUrl = (imageUrl: string): string => {
  if (!imageUrl || !isRemoteDndImage(imageUrl)) {
    return imageUrl;
  }

  const encodedSource = encodeURIComponent(imageUrl);
  return `https://images.weserv.nl/?url=${encodedSource}&w=332&h=332&fit=cover&output=webp&q=65`;
};
