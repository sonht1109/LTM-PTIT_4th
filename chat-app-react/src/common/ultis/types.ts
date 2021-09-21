export interface IUser {
  avatar?: string;
  created_at: string;
  email?: string;
  id: number;
  last_online?: string;
  username: string;
}

export interface IFriend {
  blocking_id?: string;
  confirmed?: boolean;
  id: number;
  friend: IUser;
}