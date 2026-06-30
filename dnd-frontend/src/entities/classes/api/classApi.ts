import { apiClient } from '../../../shared/api/apiClient.ts';
import type { PageResponse } from '../../../shared/api/types.ts';
import type { DndClass } from '../model/types';

export const getClasses = (page = 0, size = 20) =>
  apiClient
    .get<PageResponse<DndClass>>("/api/classes", { params: { page, size } })
    .then((r) => r.data);

export const getClassByIndex = (index: string) =>
  apiClient.get<DndClass>(`/api/classes/${index}`).then((r) => r.data);