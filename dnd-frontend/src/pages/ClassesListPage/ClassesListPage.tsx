import { CategoryListPage } from "../CategoryListPage/CategoryListPage";
import { useClassesQuery } from "../../entities/classes/model/hooks";

export const ClassesListPage = () => {
  const { data, isLoading, error } = useClassesQuery();

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Failed to load classes</div>;

  const items = (data?.content ?? []).map((c) => ({
    id: c.index,
    title: c.name,
    path: `/character/classes/${c.index}`,
    icon: "/images/icons/placeholders/character.png",
  }));

  return <CategoryListPage title="Classes" items={items} />;
};