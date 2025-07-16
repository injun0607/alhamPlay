export interface CommonResponse<T> {
  status: StatusType; // HttpStatus에 해당, 서버에서 string으로 내려옴
  message?: string | null;
  data?: T | null;
}


export const statusTypes = [
    "OK",
    "FOUND",
    "NOT_FOUND",
    "BAD_REQUEST",
    "UNAUTHORIZED",
    "FORBIDDEN",
    "INTERNAL_SERVER_ERROR",
    "BAD_GATEWAY",
    "SERVICE_UNAVAILABLE",
    "GATEWAY_TIMEOUT",
] as const;

export type StatusType = typeof statusTypes[number];