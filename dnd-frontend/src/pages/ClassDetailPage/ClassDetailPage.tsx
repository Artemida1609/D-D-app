import { useParams } from "react-router-dom";
import { PageTitle } from "../../shared/ui/PageTitle";
import { useClassByIndexQuery } from "../../entities/classes/model/hooks";

export const ClassDetailPage = () => {
  const { index } = useParams<{ index: string }>();
  const { data: dndClass, isLoading, error } = useClassByIndexQuery(index);

  if (isLoading) return <div>Loading...</div>;
  if (error || !dndClass) return <div>Class not found</div>;

  return (
    <div className="w-full flex flex-col flex-1 pb-20">
      <PageTitle title={dndClass.name} />
      <p>Hit die: d{dndClass.hit_die}</p>
      {/* далі по аналогії з твоїм DetailPage — характеристики, опис */}
    </div>
  );
};