import { useQuery } from "@tanstack/react-query";
import { getClasses, getClassByIndex } from "../api/classApi.ts";

export const useClassesQuery = (page = 0, size = 20) =>
  useQuery({
    queryKey: ["classes", page, size],
    queryFn: () => getClasses(page, size),
  });

export const useClassByIndexQuery = (index: string | undefined) =>
  useQuery({
    queryKey: ["class", index],
    queryFn: () => getClassByIndex(index!),
    enabled: !!index,
  });