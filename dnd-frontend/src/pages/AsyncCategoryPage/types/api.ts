export interface ApiListItem {
  name?: string;
  title?: string;
  index?: string | number;
  id?: string | number;
  url?: string;
  path?: string;
  type?: string;
  category?: string;
  description?: string;
  fullName?: string;
  slug?: string;
  image?: string;
  imageUrl?: string;
  icon?: string;
  school?: string | { index?: string; name?: string; url?: string };
  equipment?: ApiListItem[];
  [key: string]: unknown;
}

export interface ApiListResponse {
  content?: ApiListItem[];
  results?: ApiListItem[];
  data?: ApiListItem[];
  totalPages?: number;
  last?: boolean;
}

export interface AsyncListItem {
  id: string;
  title: string;
  path: string;
  icon: string;
  searchText: string;
  itemIndex?: string;
  weaponCategory?: string;
  weaponRange?: string;
  categoryRange?: string;
  armorCategory?: string;
  gearCategory?: string | object;
  equipmentCategoryIndex?: string;
}
