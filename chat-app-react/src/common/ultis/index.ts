import { AxiosError } from "axios";

export const handleError = (err: AxiosError) => {
  console.log(err)
}

export const avatarSrc = (src: string ) => {
  if(src !== "") {
    return src;
  }
  return "/images/avt-placeholder.png";
}