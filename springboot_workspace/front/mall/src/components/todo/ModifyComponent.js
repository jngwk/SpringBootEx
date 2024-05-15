import { useEffect, useState } from "react";
const initState = {
  tno: 0,
  title: "",
  writer: "",
  dueDate: "null",
  complete: false,
};
const ModifyComponent = ({ tno }) => {
  const [todo, setTodo] = useState({ ...initState });
  useEffect(() => {}, [tno]);
  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">
      <div className="flex justify-end p-4">
        <button
          type="button"
          className="rounded p-4 m-2 bg-red-500 w-32 text-xl text-
white"
        >
          Delete
        </button>

        <button
          type="button"
          className="rounded p-4 m-2 bg-blue-500 w-32 text-xl text-
white"
        >
          Modify
        </button>
      </div>
    </div>
  );
};
export default ModifyComponent;
