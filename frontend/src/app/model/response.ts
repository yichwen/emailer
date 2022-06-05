export interface ApiResponse {
  code: number,
  message: string,
  data: any
}

export interface PageData {
  page: number;
  size: number;
  totalPages: number;
  totalElements: number;
  data: any;
}