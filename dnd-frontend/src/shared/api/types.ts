export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number; // поточна сторінка (0-based)
  size: number;
  first: boolean;
  last: boolean;
}
