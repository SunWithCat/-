// frontend/src/types/book.ts
export interface Book {
  bookId: number;
  title: string;
  author: string;
  isbn: string;
  publisher: string;
  publicationYear: number;
  status: 'AVAILABLE' | 'BORROWED' | 'RESERVED';
  coverUrl?: string;
  quantity: number;
}
