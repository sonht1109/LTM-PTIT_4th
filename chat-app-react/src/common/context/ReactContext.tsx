import React, { createContext, ReactNode, useState } from "react";

interface IReactsContenxt {
  messageId: number | null;
  setMessageId: React.Dispatch<React.SetStateAction<number | null>>;
}

export const ReactsContext = createContext<IReactsContenxt>(
  {} as IReactsContenxt
);

const ReactsProvider = ({ children }: { children: ReactNode }) => {
  const [messageId, setMessageId] = useState<number | null>(null);

  return (
    <ReactsContext.Provider
      value={{ messageId, setMessageId }}
    >
      {children}
    </ReactsContext.Provider>
  );
};

export default ReactsProvider;
