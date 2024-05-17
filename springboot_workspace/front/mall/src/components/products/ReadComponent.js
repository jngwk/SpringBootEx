import { useEffect, useState } from "react";
import { getOne, API_SERVER_HOST } from "../../api/productsApi";
import useCustomMove from "../../hooks/useCustomMove";
import PageComponent from "../todo/PageComponent";

const initState = {
  pno: 0,
  pname: "",
  pdesc: "",
  price: 0,
  uploadFileNames: [],
};
// const host = API_SERVER_HOST;
const ReadComponent = ({ pno }) => {
  const [product, setProduct] = useState(initState);
  const { moveToList, moveToModify } = useCustomMove();
  useEffect(() => {
    getOne(pno).then((data) => {
      console.log(data);
      setProduct(data);
    });
  }, [pno]);
  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">
      {makeDiv("PNO", product.pno)}
      {makeDiv("PNAME", product.pname)}
      {makeDiv("PRICE", product.price)}
      {makeDiv("PDESC", product.pdesc)}
      <div className="flex justify-end p-4">
        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
          onClick={() => moveToList(pno)}
        >
          List
        </button>
        <button
          type="button"
          className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500"
          onClick={() => moveToModify(pno)}
        >
          {" "}
        </button>
      </div>
    </div>
  );
};
const makeDiv = (title, value) => (
  <div className="flex justify-center">
    <div className="relative mb-4 flex w-full flex-wrap items-stretch">
      <div className="w-1/5 p-6 text-right font-bold">{title}</div>
      <div className="w-4/5 p-6 rounded-r border border-solid shadow-md">
        {value}
      </div>
    </div>
  </div>
);

export default ReadComponent;
