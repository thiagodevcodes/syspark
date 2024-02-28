import styles from "./mobileButton.module.css"

interface MobileButtonProps {
    setActive: React.Dispatch<React.SetStateAction<boolean>>;
    active: boolean;
}

export default function MobileButton({ setActive, active }:MobileButtonProps) {
    const handleClick = () => {
        setActive(!active)
    }

    return (
        <button className={styles.button} onClick={handleClick}>
            <div className={`${styles.line1} ${active ? `${styles.active}` : ""}`}></div>
            <div className={`${styles.line2} ${active ? `${styles.active}` : ""}`}></div>
            <div className={`${styles.line3} ${active ? `${styles.active}` : ""}`}></div>
        </button>
    );
}