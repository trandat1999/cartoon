export interface AuthRequest {
  username?: string;
  password?: string;
}
export interface AuthResponse {
  accessToken?: string;
  refreshToken?: string;
}
export interface ForgotPasswordRequest {
  username?: string;
  email?: string;
}
