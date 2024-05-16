import React, { useState } from "react";

function ConfirmButtonFnComponent(props){
    const [isConfirmed, setIsConfirmed] = useState(false);

    const handleConfirm = () => {
        setIsConfirmed((prevIsConfirmed) => !prevIsConfirmed);
    }

    return(
        <button onClick={handleConfirm} disabled={isConfirmed}>
            {isConfirmed ? "Confirmed" : "Confirm"}
        </button>
    )
}

export default ConfirmButtonFnComponent;