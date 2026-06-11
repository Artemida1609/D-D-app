type ArrowLeftProps = {
  size?: number | string;
};

export const ArrowLeft = ({
  size = 24,
}: ArrowLeftProps) => {
  return (
    <svg
      width={size}
      height={size}
      viewBox="0 0 24 24"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      preserveAspectRatio="xMidYMid meet"
    >
      <path
        d="M17.5453 0.192139C17.7469 0.192139 17.9531 0.271826 18.1078 0.426514C18.4172 0.735889 18.4172 1.24214 18.1078 1.55151L7.51875 12.1406L17.9531 22.575C18.2625 22.8843 18.2625 23.3906 17.9531 23.7C17.6438 24.0093 17.1375 24.0093 16.8281 23.7L5.82656 12.7031C5.51719 12.3937 5.51719 11.8875 5.82656 11.5781L16.9781 0.426514C17.1375 0.267139 17.3391 0.192139 17.5453 0.192139Z"
        fill="currentColor"
      />
    </svg>
  );
};