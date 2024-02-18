export const ACCESS_TOKEN = "access_token";
export const REFRESH_TOKEN = "refresh_token";
export const LANGUAGE = "language";
export const LANGUAGE_EN = "en";
export const GutterOption = {xs: 8, sm: 16, md: 24, lg: 32};
export const VOIDED_CHOICE = [
  {value: false, label: "common.voidedFalse"},
  {value: true, label: "common.voidedTrue"},
]
export const getBase64 = (file: File): Promise<string | ArrayBuffer | null> =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
