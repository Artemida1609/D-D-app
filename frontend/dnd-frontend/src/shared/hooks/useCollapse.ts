import { useEffect, useRef, useState } from "react";

export const useCollapse = (isOpen: boolean) => {
  const ref = useRef<HTMLUListElement>(null);
  const [height, setHeight] = useState("0px");

  useEffect(() => {
    setHeight(isOpen ? `${ref.current?.scrollHeight}px` : "0px");
  }, [isOpen]);

  return { ref, height };
};