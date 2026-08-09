export const ROWS_PER_PAGE = 6;

export const SIZE_BREAKPOINTS = {
  mobile: 361,
  mobilCardWidth: 172,
  desktopCardWidth: 230,
  mobileGap: 16,
  desktopGap: 32,
} as const;

export const GRID_CALC = {
  minColumns: 1,
  maxColumns: 6,
  resizeDebounceMs: 120,
} as const;
