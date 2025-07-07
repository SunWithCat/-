export interface User {
  userId: number;
  username: string;
  email: string;
  role: 'USER' | 'ADMIN' | 'STUDENT' | 'TEACHER';
  isVerified: boolean;
}
