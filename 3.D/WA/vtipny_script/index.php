<?php
function detectThreatLevel($input)
{
    $level = 0;
    $patterns = [
        // SQL inj
        '/(\bSELECT\b|\bUNION\b|\bDROP\b|\bINSERT\b|\bUPDATE\b|\bDELETE\b)/i' => 3,
        '/(\bOR\b\s+1\s*=\s*1|\bAND\b\s+1\s*=\s*1)/i' => 3,
        '/(--|#|\/\*|\*\/)/' => 2,
        "/('|\"|;)/" => 1,

        // XSS
        '/(<script|<iframe|<object|<embed)/i' => 4,
        '/(javascript:|onerror|onload|onclick)/i' => 3,
        '/(<img|<svg|<video)/i' => 2,

        // Comma ing
        '/(\||;|`|\$\(|&&)/i' => 3,
        '/(\.\.\/|\.\.\\\\)/' => 2,

        // Path trav
        '/(\/etc\/passwd|\/proc\/|C:\\\\Windows)/i' => 4,

        // sus
        '/(<\?php|<%|asp)/i' => 3,
        '/(eval\(|exec\(|system\()/i' => 4,
    ];

    foreach ($patterns as $pattern => $threat) {
        if (preg_match($pattern, $input)) {
            $level = max($level, $threat);
        }
    }

    return $level;
}

function sanitizeOutput($input)
{
    return htmlspecialchars($input, ENT_QUOTES, 'UTF-8');
}

session_start();

if (!isset($_SESSION['history'])) {
    $_SESSION['history'] = [];
}

$threatLevel = 0;
$userInput = '';
$message = '';
$playAudio = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['user_input'])) {
    $userInput = $_POST['user_input'];
    $threatLevel = detectThreatLevel($userInput);

    $messages = [
        0 => 'Start to complete lyrcis of song!',
        1 => 'WE',
        2 => 'ARE',
        3 => 'CHARLIE',
        4 => 'KIRK!'
    ];

    $message = $messages[$threatLevel];

    $_SESSION['history'][] = [
        'level' => $threatLevel,
        'message' => $message
    ];

    $achievedLevels = array_unique(array_column($_SESSION['history'], 'level'));
    sort($achievedLevels);

    if ($achievedLevels === [0, 1, 2, 3, 4]) {
        $playAudio = true;
    }
}

if (isset($_POST['reset'])) {
    $_SESSION['history'] = [];
    header('Location: ' . $_SERVER['PHP_SELF']);
    exit;
}

$images = [
    0 => 'imgs/level0.jpg',
    1 => 'imgs/level1.jpg',
    2 => 'imgs/level2.jpg',
    3 => 'imgs/level3.jpg',
    4 => 'imgs/level4.jpg',
];

$currentImage = $images[$threatLevel];
?>
<!DOCTYPE html>
<html lang="cs">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Try hack me</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            min-height: 100vh;
            padding: 20px;
        }

        .main-container {
            display: flex;
            gap: 20px;
            max-width: 1400px;
            margin: 0 auto;
        }

        .left-section {
            flex: 1;
            background: white;
            padding: 40px;
        }

        .right-section {
            width: 350px;
            background: white;
            padding: 30px;
            max-height: 90vh;
            overflow-y: auto;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 2em;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 1.3em;
            padding-bottom: 10px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: 600;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px 15px;
            font-size: 16px;
        }

        input[type="text"]:focus {
            outline: none;
        }

        button {
            width: 100%;
            padding: 12px;
            color: white;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
            margin-bottom: 10px;
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        button:active {
            transform: translateY(0);
        }

        .reset-btn {
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%);
        }

        .image-container {
            margin-top: 20px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .image-container img {
            width: 100%;
            height: auto;
            display: block;
        }

        .history-item {
            padding: 15px;
            margin-bottom: 10px;
            animation: slideIn 0.3s ease-out;
        }

        @keyframes slideIn {
            from {
                transform: translateX(20px);
                opacity: 0;
            }

            to {
                transform: translateX(0);
                opacity: 1;
            }
        }

        .history-item.level-0 {
            background: #d4edda;
            border-color: #28a745;
            color: #155724;
        }

        .history-item.level-1 {
            background: #fff3cd;
            border-color: #ffc107;
            color: #856404;
        }

        .history-item.level-2 {
            background: #ffd8a8;
            border-color: #ff9800;
            color: #d84315;
        }

        .history-item.level-3 {
            background: #ffcccc;
            border-color: #f44336;
            color: #c62828;
        }

        .history-item.level-4 {
            background: #ffb3b3;
            border-color: #d32f2f;
            color: #b71c1c;
            font-weight: bold;
        }

        .history-item strong {
            display: block;
            margin-bottom: 5px;
            font-size: 0.9em;
            opacity: 0.8;
        }

        .completion-message {
            color: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
            animation: pulse 1s infinite;
        }

        @keyframes pulse {

            0%,
            100% {
                transform: scale(1);
            }

            50% {
                transform: scale(1.05);
            }
        }

        .empty-history {
            text-align: center;
            color: #999;
            padding: 40px 20px;
            font-style: italic;
        }

        @media (max-width: 1024px) {
            .main-container {
                flex-direction: column;
            }

            .right-section {
                width: 100%;
                max-height: 500px;
            }
        }
    </style>
</head>

<body>
    <div class="main-container">
        <div class="left-section">
            <h1>Try hack me</h1>

            <form method="POST">
                <div class="form-group">
                    <label for="user_input">Input:</label>
                    <input type="text" id="user_input" name="user_input"
                        value="<?php echo sanitizeOutput($userInput); ?>"
                        placeholder="Např.: ahoj, ' OR 1=1--, <script>alert('XSS')</script>" required>
                </div>
                <button type="submit">SEND</button>
            </form>

            <form method="POST" style="margin-top: 10px;">
                <button type="submit" name="reset" class="reset-btn">Reset</button>
            </form>

            <div class="image-container">
                <img src="<?php echo $currentImage; ?>" alt="Reakce serveru" onerror="this.src='imgs/level0.jpg'">
            </div>
        </div>

        <div class="right-section">
            <h2>History</h2>

            <?php if ($playAudio): ?>
                <div class="completion-message">
                    ALL LEVELS COMPLETED!
                </div>
                <audio id="victoryAudio" autoplay>
                    <source src="audio/victory.mp3" type="audio/mpeg">
                </audio>
            <?php endif; ?>

            <?php if (empty($_SESSION['history'])): ?>
                <div class="empty-history">
                    No attempts yet...<br>
                    Try to "hack" the system!
                </div>
            <?php else: ?>
                <?php foreach (array_reverse($_SESSION['history']) as $index => $item): ?>
                    <div class="history-item level-<?php echo $item['level']; ?>">
                        <strong>Attempt #<?php echo count($_SESSION['history']) - $index; ?> - Level
                            <?php echo $item['level']; ?></strong>
                        <?php echo $item['message']; ?>
                    </div>
                <?php endforeach; ?>
            <?php endif; ?>
        </div>
    </div>
</body>

</html>