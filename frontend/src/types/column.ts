export interface Column<T> {
  key: keyof T | "actions";
  title: string;
  render?: (value: any, record: T) => React.ReactNode;
}
